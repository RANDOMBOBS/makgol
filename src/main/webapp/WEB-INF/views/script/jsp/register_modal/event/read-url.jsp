<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const readURL = () => {
        $("#photo").change((event) => {
            const file = event.target.files[0]
            const reader = new FileReader();

            reader.onload = function (e) {
                $("#photo_label").css({
                    backgroundColor: "black",
                    backgroundImage: "url(" + e.target.result + ")",
                    backgroundRepeat: "no-repeat",
                    backgroundPosition: "0 0",
                    backgroundSize: "cover"
                })
            }
            
            reader.readAsDataURL(file);
        })
    }
</script>