function fnSend() {
    $.ajax({
        type: 'POST',
        url: "/spl-ch-trnsl/request-tr-pp",
        dataType: 'json',
        data: $("#form").serialize(),
    }).done(function(result) {
        console.log(result);
        $('#output').val(result.translatedText);
        $('#original-text').val(result.originalText);
        $('#corrected-original-text').val(result.correctedText);
    }).fail(function (error) {
        $('#output').val("에러가 발생하였습니다. 잠시 후 다시 시도해 주세요.");
    });
}