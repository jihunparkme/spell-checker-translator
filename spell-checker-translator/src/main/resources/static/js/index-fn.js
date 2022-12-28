function fnSend() {
    $.ajax({
        type: 'POST',
        url: "/spl-ch-trnsl/request",
        dataType: 'json',
        data: $("#form").serialize(),
    }).done(function(result) {
        console.log(result);

        translatedText = result.translatedText.replace("\\\"n", "\n")
        $('#output').val(translatedText);
        $('#corrected-original-text').val(result.originalText);
    }).fail(function (error) {
        $('#output').val("에러가 발생하였습니다. 잠시 후 다시 시도해 주세요.");
    });
}