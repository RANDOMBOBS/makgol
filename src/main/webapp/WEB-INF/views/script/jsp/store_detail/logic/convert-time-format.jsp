<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const convertTimeFormat = (timeString) => {
        console.log(timeString)
        const [start, end] = timeString.split(" ~ ");

        // 24시간 형식을 12시간 형식으로 변환
        const convertTo12HourFormat = (time) => {
            const [hour, minute] = time.split(":");
            const formattedHour = hour % 12 || 12;
            return formattedHour + ":" + minute + (hour < 12 ? ' AM' : ' PM');
        };

        const convertedStart = convertTo12HourFormat(start);
        const convertedEnd = convertTo12HourFormat(end);

        return convertedStart + "~" + convertedEnd;
    }
</script>