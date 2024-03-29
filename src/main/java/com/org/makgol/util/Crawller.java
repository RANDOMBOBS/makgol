package com.org.makgol.util;


import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.org.makgol.stores.vo.StoreRequestMenuVo;
import com.org.makgol.stores.vo.StoreRequestVo;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
public class Crawller {

    private int thread_count = 10;
	/**
	 * @param storeRequestVos<StoreRequestVo> storeRequestVos
	 * 				------StoreRequestVo---------
	 * 				...
	 * 				...
	 * 				...
	 * 				...
	 * 
	 * @return HashMap<String, Object>
	 * 				---------HashMap KEY--------
	 * 				식당 정보 : "store_info_" +index
	 * 				식당 메뉴 : "store_menu_" +index
	 * 				-----------------------------
	 * @throws Exception
	 */


    public HashMap<String, Object> new_crawller(List<StoreRequestVo> storeRequestVos) throws Exception {
    	// 결과를 저장할 HashMap 생성
        HashMap<String, Object> hashMap = new HashMap<>();

        // 드라이버 경로 윈도우
        //String driverPath = "src\\main\\java\\com\\org\\makgol\\driver\\chromedriver.exe";
        //String driverPath = "D:\\sunBoot\\makgol\\src\\main\\java\\com\\org\\makgol\\driver\\chromedriver.exe";
        // 드라이버 절대경로 맥
        //String driverPath = "src/main/java/com/org/makgol/driver/chromedriver_mac";
        // 드라이버 절대경로 linux(ubuntu)
        String driverPath = "/home/ubuntu/service/makgol/src/main/java/com/org/makgol/driver/chromedriver_linux";

        //스레드를 종료하기위한 List
    	List<Thread> jobThreads = new ArrayList<>();

        if(storeRequestVos.size() < thread_count){
            thread_count = storeRequestVos.size();
        }
        int storesSize = storeRequestVos.size() / thread_count;
        // 쓰레드 풀 생성
        ExecutorService executor = Executors.newFixedThreadPool(thread_count);
            //storeRequestVos의 사이즈 만큼스레드를 생성하겠다.
            for (int i = 1; i <= thread_count; i++) {
                int startIndex = i * (storeRequestVos.size() / thread_count);
                int endIndex = (i + 1) * (storeRequestVos.size() / thread_count) - 1;

                // 마지막 스레드는 남은 작업을 모두 처리
                if (i == thread_count - 1) {
                    endIndex = storeRequestVos.size() - 1;
                }

                // 스레드에 작업을 할당하고 실행
                Runnable worker = new WorkerThread(startIndex, endIndex, storeRequestVos, hashMap, driverPath);
                executor.execute(worker);

            }

        // 쓰레드 풀 종료
        executor.shutdown();

        // 모든 작업이 완료될 때까지 대기
        while (!executor.isTerminated()) {
            // 대기
        }



        return hashMap;
    }

    private class WorkerThread implements Runnable {
        private int startIndex;
        private int endIndex;
        private List<StoreRequestVo> storeRequestVos;
        private HashMap<String, Object> hashMap;
        private String driverPath;

        public WorkerThread(int startIndex, int endIndex, List<StoreRequestVo> storeRequestVos, HashMap<String, Object> hashMap, String driverPath) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.storeRequestVos = storeRequestVos;
            this.hashMap = hashMap;
            this.driverPath = driverPath;
        }
    	@Override
        public void run(){
            try {
                // 각 스레드가 처리할 작업을 실행
                processCrawler(startIndex, endIndex);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    	 private void processCrawler(int startIndex, int endIndex) {
            for (int i = startIndex; i <= endIndex; i++){
            Random r = new Random();

    		//chrome driver 경로 세팅
    		System.setProperty("webdriver.chrome.driver", driverPath);
            // 크롬 옵션 설정
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");	//브라우저가 다른 출처(origin)의 리소스에 대한 요청을 수행할 수있음.
            options.addArguments("headless"); 					//브라우저 창을 표시하지 않고 백그라운드에서 웹 페이지를 실행하는 모드.
            options.addArguments("--disable-popup-blocking");	//팝업 차단을 비활성화하는 옵션.
            options.addArguments("--blink-settings=imagesEnabled=false");//웹 페이지에서 이미지 로딩을 비활성화.

            WebDriver driver = new ChromeDriver(options);

            driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS); // 30초로 시간 초과 설정



                // 식당의 메뉴들를 저장할 List
                List<StoreRequestMenuVo> storeRequestMenuVos = new ArrayList<StoreRequestMenuVo>();

                StoreRequestVo storeRequestVo = storeRequestVos.get(i);

                String detailPage = storeRequestVo.getPlace_url();
            // 웹 페이지로 이동 후 코드 가져옴
            driver.get(detailPage);

            // 로딩 기다리기
            try {
            	Thread.sleep(2000);
            } catch (Exception e) {
            	e.printStackTrace();
            }

            // 검색 버튼을 찾아서 클릭

            WebElement searchButton = null;
            WebElement element = null;
            try {
                searchButton = driver.findElement(By.xpath("//*[@id=\"mArticle\"]/div[3]/a"));
                searchButton.click();
                element 			= searchButton.findElement(By.xpath("//*[@id=\"mArticle\"]"));
            } catch (Exception e) {
                element 			= driver.findElement(By.cssSelector("#mArticle"));
                log.info("element");
            }

            // 웹 페이지에서 태그를 찾아서 데이터 가져오기
            WebElement span_date_revise = element.findElement(By.cssSelector("span.date_revise"));
            WebElement time_operation;
            String opening_hours;
            try {
                time_operation = element.findElement(By.cssSelector("span.time_operation"));
                opening_hours  = time_operation.getText();
                if(opening_hours == null){
                    storeRequestVo.setOpening_hours("");
                } else {
                    storeRequestVo.setOpening_hours(opening_hours);
                }
            } catch (Exception e) {log.info("span.time_operation");}


            // 크롤링 멈춤 방지용 try catch
            WebElement location_present;
            String site="";

            try {
                location_present = element.findElement(By.cssSelector("div.details_placeinfo > div.placeinfo_default.placeinfo_homepage > div > div > a"));
                site = location_present.getAttribute("href");
                if(site == null){
                    storeRequestVo.setSite("");
                } else {
                    storeRequestVo.setSite(site);
                }

    		} catch (Exception e) {}

            WebElement txt_contact;

            String phone = String.valueOf(r.nextInt(888888) + 111111);


            try {
            	txt_contact = element.findElement(By.cssSelector("span.txt_contact"));
            	phone = txt_contact.getText();
                if(phone == null || phone == ""){
                    storeRequestVo.setPhone("");
                } else {
                    storeRequestVo.setPhone(phone);
                }

    		} catch (Exception e) {log.info("span.txt_contact");}

            WebElement update_getMenu;
            String getMenu_update;
            String date_revise;
            LocalDate update_date;
            LocalDate menu_update;

            try {
            	update_getMenu = element.findElement(By.cssSelector("span.txt_updated > span"));
            	getMenu_update = update_getMenu.getText();
            	date_revise = span_date_revise.getText();

            	update_date = LocalDate.parse(date_revise.substring(0, date_revise.length() - 1), DateTimeFormatter.ofPattern("yyyy.MM.dd"));
            	menu_update = LocalDate.parse(getMenu_update.substring(0, date_revise.length() - 1),  DateTimeFormatter.ofPattern("yyyy.MM.dd"));


                if(update_date == null){
                    date_revise="0000.00.00.";
                    storeRequestVo.setMenu_update(LocalDate.parse(date_revise.substring(0, date_revise.length() - 1), DateTimeFormatter.ofPattern("yyyy.MM.dd")));
                } else {
                    storeRequestVo.setMenu_update(menu_update);
                }


                if(menu_update == null){
                    getMenu_update="0000.00.00.";
                    storeRequestVo.setMenu_update(LocalDate.parse(getMenu_update.substring(0, date_revise.length() - 1),  DateTimeFormatter.ofPattern("yyyy.MM.dd")));
                } else {
                    storeRequestVo.setUpdate_date(update_date);
                }


            } catch (Exception e) {log.info("update_date");}


            WebElement cont_photo;
            try{
                cont_photo = element.findElement(By.cssSelector("#mArticle > div.cont_photo > div.photo_area > ul > li.size_l > a"));
                String URL = cont_photo.getAttribute("style");

                // 정규표현식 패턴 설정
                String pattern = "background-image: url\\(\"//(.*?)\\?fname=(http[^\\s'\"]+)\"\\);";
                Pattern r_p = Pattern.compile(pattern);

                // 입력 문자열과 패턴을 사용하여 매칭을 찾음
                Matcher m = r_p.matcher(URL);
                if (m.find()) {
                    // 찾은 매칭을 출력
                    String editedURL = m.group(1) + m.group(2);
                    pattern = "(img1.kakaocdn.net/cthumb/local/C320x320/)(http[^\\s']+)";
                    r_p = Pattern.compile(pattern);
                    Matcher n = r_p.matcher(editedURL);

                    if (n.find()) {
                        // 찾은 매칭을 출력
                        editedURL = n.group(1) + "?fname=" + n.group(2);
                        System.out.println(editedURL);
                        storeRequestVo.setPhoto(editedURL);
                    } else {
                        // 찾은 매칭을 출력
                        pattern = "(img1.kakaocdn.net/cthumb/local/C320x320.q50/)(http[^\\s']+)";
                        r_p = Pattern.compile(pattern);
                        n = r_p.matcher(editedURL);

                        if(n.find()){
                            editedURL = n.group(1) + "?fname=" + n.group(2);
                            System.out.println(editedURL);
                            storeRequestVo.setPhoto(editedURL);
                        } else { System.out.println("매칭되는 부분을 찾을 수 없습니다.");}
                    }


                } else {
                    System.out.println("매칭되는 부분을 찾을 수 없습니다.");
                }
            }catch (Exception e){log.info("photo");}


            //데이터 확인
            log.info(" 이름 : "+ storeRequestVo.getName());
            log.info(" 주소 : "+ storeRequestVo.getAddress());
            log.info(" 도로명 : "+ storeRequestVo.getLoad_address());
            log.info(" 전화번호 : "+ storeRequestVo.getPhone());
            log.info(" 카테고리 : "+ storeRequestVo.getCategory());
            log.info(" 상세페이지 : "+ storeRequestVo.getPlace_url());
            log.info(" 업데이트 : "+ storeRequestVo.getUpdate_date());
            log.info(" 영업시간 : "+ storeRequestVo.getOpening_hours());
            log.info(" 메뉴 업데이트 : "+ storeRequestVo.getMenu_update());
            log.info(" 이미지 : "+ storeRequestVo.getPhoto());


            // 메뉴 정보 가져오기
            List<WebElement> element_menu = element.findElements(By.cssSelector("#mArticle > div.cont_menu > ul > li"));
            for (WebElement element_menu_ : element_menu) {
                StoreRequestMenuVo storeRequestMenuVo = new StoreRequestMenuVo();


            	//메뉴 이름이 담긴 태그 타겟팅
                WebElement menu_name = element_menu_.findElement(By.cssSelector("div > span"));



                WebElement menu_price;
                String price;
                try{
                    //메뉴 가격이 담긴 태그 타겟팅
                    menu_price = element_menu_.findElement(By.cssSelector("div > em.price_menu"));
                    //메뉴 가격 가져오기
                    price = menu_price.getText();
                    if(price == null){
                        storeRequestMenuVo.setPrice("");
                    } else {
                        storeRequestMenuVo.setPrice(price);
                    }

                } catch (Exception e){}


                //메뉴 이름 가져오기
                String menu = menu_name.getText();
                if(menu == null){
                    storeRequestMenuVo.setMenu("");
                } else {
                    storeRequestMenuVo.setMenu(menu);
                }

                //식당의 메뉴를 저장할 객체
                //메뉴 객체에 담기
                storeRequestMenuVos.add(storeRequestMenuVo);
            }

            // HashMap에 결과 저장
                //log.info("before hash put : " + thread_count + " : " + storeRequestVo.getName());

                synchronized (this){
                    hashMap.put("store_menu_" + i, storeRequestMenuVos);
                    hashMap.put("store_info_" + i, storeRequestVo);
                }

                log.info("hashMap.size() --> : " + hashMap.size()/2);

                try{Thread.sleep(500);}catch(Exception e){}
                driver.close();
                driver.quit();
                try{Thread.sleep(500);}catch(Exception e){}
            }
            // WebDriver 종료
    	}
    }
}
