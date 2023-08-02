// var main = {...} 선언 이유 -> 브라우저의 스코프는 공용공간으로 쓰이기 때문에 나중에 덮어써질 수있으므로 index.js 만의 유효범위를 만들어서 사용함. 다른 JS와 겹칠 위험이 사라짐.
// 이런 식의 프론트엔드의 의존성 관리, 스코프 관리 등의 문제들로 이러한 기능들은 이미 프레임워크 레벨에서 지원 중임.
var main = {
    init: function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
    },
    save: function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
};

main.init();