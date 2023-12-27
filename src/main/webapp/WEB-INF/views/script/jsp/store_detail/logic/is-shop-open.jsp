<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const isShopOpen = (openTime) => {
        const now = new Date();
        const [start, end] = openTime.split("~");

        const currentStart = start.trim().slice(0, -3);
        const currentEnd = end.trim().slice(0, -3);

        const hours = now.getHours();
        return hours >= currentStart && hours < currentEnd;


        // const start24Hour = convert12HourTo24Hour(start)
        // const end24Hour = convert12HourTo24Hour(end)
        //
        // console.log(start24Hour)
        // console.log(end24Hour)

        // const hours = now.getHours();
        // console.log(hours)
        // console.log(completeStart)
        // console.log(completeEnd)
        //
        //
        // const result = hours >= completeStart && hours < completeEnd;
        // console.log(result);
        // return result

        // console.log(now)
        // console.log(completeStart)
        // console.log(completeEnd)

        // const currentYear = now.getFullYear();
        // const currentMonth = now.getMonth() + 1;
        // const currentDay = now.getDate();
        //
        // const currentStart = currentYear + "-" + currentMonth + "-" + currentDay + " " + start;
        // const currentEnd = currentYear + "-" + currentMonth + "-" + currentDay + " " + ew;
        //
        // const startTime = new Date(currentStart);
        // const endTime = new Date(currentEnd);
        //
        // console.log(now)
        // console.log(startTime)
        // console.log(endTime)
        //
        // const result = now >= startTime && now <= endTime;
        // console.log(result)
        // return result;
    }
</script>