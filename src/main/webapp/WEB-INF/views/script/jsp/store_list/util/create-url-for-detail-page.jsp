<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    class UrlBuilderForDetail {
        #defaultUrl = "<c:url value='/store/detail' />?";

        setPlaceName(place_name) {
            const placeNameQuery = "place_name=" + place_name + "&";
            this.#defaultUrl += placeNameQuery;
            return this;
        }

        setPhone(phone) {
            const phoneQuery = "phone=" + phone + "&";
            this.#defaultUrl += phoneQuery;
            return this;
        }

        setAddressName(address_name) {
            const addressNameQuery = "address_name=" + address_name + "&";
            this.#defaultUrl += addressNameQuery;
            return this;
        }

        setCategoryName(category_name) {
            const categoryName = "category_name=" + category_name + "&";
            this.#defaultUrl += categoryName;
            return this;
        }

        setPlaceUrl(place_url) {
            const placeUrl = "place_url=" + place_url + "&";
            this.#defaultUrl += placeUrl;
            return this;
        }

        setShopX(shop_x) {
            const shopX = "shop_x=" + shop_x + "&";
            this.#defaultUrl += shopX;
            return this;
        }

        setShopY(shop_y) {
            const shopY = "shop_y=" + shop_y + "&";
            this.#defaultUrl += shopY;
            return this;
        }

        setMyX(my_x) {
            const myX = "my_x=" + my_x + "&";
            this.#defaultUrl += myX;
            return this;
        }

        setMyY(my_y) {
            const myY = "my_y=" + my_y + "&";
            this.#defaultUrl += myY;
            return this;
        }

        setDistance(distance) {
            const _distance = "distance=" + distance;
            this.#defaultUrl += _distance;
            return this;
        }

        getUrl() {
            return this.#defaultUrl;
        }
    }

    const createUrlForDetailPage = (shop) => {

        const setShopCoordinate = (shop) => {
            const {longitude, latitude} = shop;
            const shopX = longitude;
            const shopY = latitude;
            return {shopX, shopY};
        }

        const {place_name, phone, address_name, category_name, place_url, distance} = shop;
        const {shopX, shopY} = setShopCoordinate(shop);

        const setMyCoordinate = (myCoordinate) => {
            const {longitude, latitude} = myCoordinate;
            const myX = longitude;
            const myY = latitude;
            return {myX, myY};
        }

        const myCoordinate = JSON.parse(localStorage.getItem("myCoordinate"));
        const {myX, myY} = setMyCoordinate(myCoordinate);

        return new UrlBuilderForDetail()
            .setPlaceName(place_name)
            .setPhone(phone)
            .setAddressName(address_name)
            .setCategoryName(category_name)
            .setPlaceUrl(place_url)
            .setShopX(shopX)
            .setShopY(shopY)
            .setMyX(myX)
            .setMyY(myY)
            .setDistance(distance)
            .getUrl();
    };

</script>