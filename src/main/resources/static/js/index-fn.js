function fnSend() {
    $.ajax({
        type: 'POST',
        url: "/spl-ch-trnsl/request-tr-pp",
        dataType: 'json',
        data: $("#form").serialize(),
    }).done(function(result) {
        console.log(result);
        let originalText = result.originalText.replaceAll("\n", "<br>");
        let correctedText = result.correctedText.replaceAll("\n", "<br>");
        result.spellCheckErrInfo.forEach((errInfo) => {
            originalText = originalText.replace(errInfo.orgStr, "<span style='color: red'>" + errInfo.orgStr + "</span>");
        });

        $('#output').val(result.translatedText);
        $('#original-text').html(originalText);
        $('#corrected-text').html(correctedText);
    }).fail(function (error) {
        $('#output').val("에러가 발생하였습니다. 잠시 후 다시 시도해 주세요.");
    });
}