<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    class UrlBuilderForDetail {
        #defaultUrl = "<c:url value='/store/detail' />?";

        setShopId(shop_id) {
            const shopId = "shop_id=" + shop_id + "&";
            this.#defaultUrl += shopId;
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

        const {id, distance} = shop;
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
            .setShopId(id)
            .setShopX(shopX)
            .setShopY(shopY)
            .setMyX(myX)
            .setMyY(myY)
            .setDistance(distance)
            .getUrl();
    };

</script>