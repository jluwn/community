
/**
 * 提交回复
 */
function post() {
    var questionId = $("#question_id").val();//此处自己发过CSDN
    var content = $("#comment_content").val();
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId": questionId,
            "content": content,
            "type": 1
        }),
        success:function (response) {
            if (response.code == 200) {
                $("#comment_content").hide();
            }else {
                if (response.code == 2003) {
                    var isAccepted = confirm(response.message);
                    if (isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=Iv1.8b1644484a009bb8&redirect_uri=http://localhost:8080/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", true);
                    }
                } else {
                    alert(response.message);
                }

            }
           // console.log(response);
        },
        dataType :"json"
    });
    // comment2target(questionId, 1, content);
    // console.log(questionId);
    // console.log(content);
}