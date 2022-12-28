const R_TEXT = /([^<>]+)(?=(<|$))/g;
const R_WORD = /[a-zA-Z]+/g;
const VOWELS = 'aeiou';

const text = document.querySelector('#text');
const clear = document.querySelector('#clear');
const output = document.querySelector('#output');
const copy = document.querySelector('#copy');

const traverse = (object, callback) => {
    for (let property in object) {
        let value = object[property];
        if (typeof value === 'object') {
            traverse(value, callback);
        } else {
            object[property] = callback(value);
        }
    }
};

const translateHtml = (html) => {
    return html.replace(R_TEXT, translateText);
};

const translateText = (match, text) => {
    return text.replace(R_WORD, translateWord);
};

const translateWord = (word) => {
    if (isVowel(word, 0)) {
        return word + 'way';
    } else {
        let index = 1;
        while (index < word.length && !isVowel(word, index)) {
            index++;
        }
        const translated = word.substr(index) + word.substr(0, index).toLowerCase() + 'ay';
        const firstCharacter = word.substr(0, 1);
        if (firstCharacter === firstCharacter.toUpperCase()) {
            return translated.substr(0, 1).toUpperCase() + translated.substr(1);
        } else {
            return translated;
        }
    }
};

const isVowel = (word, index) => {
    const letter = word.substr(index, 1).toLowerCase();
    return -1 < VOWELS.indexOf(letter) || (index && letter === 'y');
};

clear.onclick = () => {
    text.value = '';
    output.value = '';
};

copy.onclick = () => {
    output.select();
    document.execCommand('copy');
};