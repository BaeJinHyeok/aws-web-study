// var main = {...} 선언 이유 -> 브라우저의 스코프는 공용공간으로 쓰이기 때문에 나중에 덮어써질 수있으므로 index.js 만의 유효범위를 만들어서 사용함. 다른 JS와 겹칠 위험이 사라짐.
// 이런 식의 프론트엔드의 의존성 관리, 스코프 관리 등의 문제들로 이러한 기능들은 이미 프레임워크 레벨에서 지원 중임.
var main = {
    init: function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
        $('#btn-update').on('click', function () { // btn-update란 id 를 가진 HTML 엘리멘트에 click 이벤트가 발생할때 update function 을 실행하도록 이벤트를 등록.
            _this.update();
        });
        $('#btn-delete').on('click', function () {
            _this.delete();
        });
        $('#btn-deleteall').on('click', function () {
            _this.deleteall();
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

    update: function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT', // 여러 HTTP 메소드 중 PUT 메소드를 선택 -> PostsApiController에 있는 API에서 이미 @PutMapping 으로 선언했기 때문에 PUT을 사용해야함
                         // 참고로 이는 PEST 규약에 맞게 설정된 것임.
                         // REST에서 CRUD는 다음과 같이 HTTP Method 에 매핑됨.
                         // 생성 CREATE - POST ,
                         // 읽기 READ - GET ,
                         // 수정 UPDATE - PUT ,
                         // 삭제 DELETE - DELETE
                         // 버튼 추가 생성시 -> 버튼 이벤트 등록 구현, function 구현 후 전체목록에서 수정 페이지로 이동할 수 있게 기능추가 한 다음 IndexController 에 update 메소드 추가.
            url: '/api/v1/posts/' + id, // 어느 게시글을 수정할지 *URL Path로 구분* 하기 위해 Path에 id 를 추가함!
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete: function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    deleteall: function () {

        var ids = []; // 선택된 ID를 저장할 배열
        var checkboxes = document.getElementsByClassName('checkbox');

        for (var i = 0; i < checkboxes.length; i++) {
            if (checkboxes[i].checked) {
                // 체크된 체크박스의 값을 배열에 추가
                // ids.push(checkboxes[i].value);
                // alert(ids);
                var id = checkboxes[i].parentNode.nextElementSibling.textContent;//.getAttribute('data-id');
                ids.push(id);
            }
        }

        if(ids.length === 0) {
            alert("선택 된 게시글이 없습니다.");
            return;
        }

        // 선택된 ID 값을 확인 (예: 콘솔에 출력)
        console.log('선택된 ID:', ids);

        $.ajax({
            type: "DELETE", // 또는 다른 HTTP 메서드 (GET, PUT, DELETE 등)
            url: '/api/v2/posts/' + ids,
            dataType: "text", // 응답 데이터 타입 // readyState = 4 , 200 정상인데 parseerror 나옴 ->  datatype을 지우거나 json에서 text로 바꿔서 해결.
            contentType: "application/json; charset=utf-8", // 요청 데이터 타입
            data: JSON.stringify(ids), // 데이터를 JSON 배열로 변환하여 전송
        }).done(function () {
            if(ids.length == 1) {
                alert("선택 된 글이 삭제되었습니다.");
            }
            else {
                alert('선택 된 글이 모두 삭제되었습니다.');
            }
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
        //     success: function (response) {
        //         // 서버 응답 처리
        //         console.log("서버 응답:", response);
        //     },
        //     error: function (error) {
        //             // 오류 처리
        //             console.error("오류 발생:", error);
        //     }
        // });
    },
}

main.init();