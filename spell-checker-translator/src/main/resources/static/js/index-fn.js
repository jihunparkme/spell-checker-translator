function fnSend() {
    $.ajax({
        type: 'POST',
        url: "/spl-ch-trnsl/request",
        dataType: 'json',
        data: $("#form").serialize(),
    }).done(function(result) {
        console.log(result);
    }).fail(function (error) {
        console.log(error);
    });
}