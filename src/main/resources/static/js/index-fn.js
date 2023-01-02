$(function () {
    $('.show').click(function () {
        $('.text-area').slideDown();
        $('.show').hide();
        $('.hide').show();
    });
    $('.hide').click(function () {
        $('.text-area').slideUp();
        $('.show').show();
        $('.hide').hide();
    });
});

const colors = [];
colors[0] = 'olive';
colors[1] = 'red';
colors[2] = 'orange';
colors[3] = 'green';
colors[4] = 'blue';
colors[5] = 'indigo';
colors[6] = 'purple';
colors[7] = 'cyan';
colors[8] = 'skyblue';
colors[9] = 'salmon';
colors[10] = 'lime';

function setResultArea(output, origTxt, crtText) {
    $('#output').val(output);
    $('#original-text').html(origTxt);
    $('#corrected-text').html(crtText);
}

function appendResultArea(originalText, translatedText) {
    let text = "<p>" + originalText + "<br/><br/>" + translatedText + "</p>"
    $(".result").after(text);
}

function fnSend() {
    setResultArea("Translating...", "Processing...", "Processing...");

    $.ajax({
        type: 'POST',
        url: "/spl-ch-trnsl/request-tr-pp",
        dataType: 'json',
        data: $("#form").serialize(),
    }).done(function (result) {
        console.log(result);
        let originalText = result.originalText.replaceAll("\n", "<br>");
        let correctedText = result.correctedText.replaceAll("\n", "<br>");
        result.spellCheckErrInfo.forEach((errInfo) => {
            originalText = originalText.replace(errInfo.orgStr, "<span style='color: " + colors[errInfo.correctMethod] + "'>" + errInfo.orgStr + "</span>");

            let candWord = errInfo.candWord.split("|")[0];
            correctedText = correctedText.replace(candWord, "<span style='color: " + colors[errInfo.correctMethod] + "'>" + candWord + "</span>");
        });

        setResultArea(result.translatedText, originalText, correctedText);
        // 레디스 적용하고 삭제!
        appendResultArea(result.originalText, result.translatedText);
    }).fail(function (error) {
        $('#output').val("에러가 발생하였습니다. 잠시 후 다시 시도해 주세요.");
    });
}
