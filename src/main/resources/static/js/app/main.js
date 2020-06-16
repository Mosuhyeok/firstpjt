var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click',function () { //버튼 등록 아이디를 btn-save로 했으니 id선택자로 클릭했을시 로직 실행
            _this.save();
        });
        $('#btn-update').on('click',function () {
            _this.update();
        });
        $('#btn-delete').on('click',function () {
            _this.delete();
        });
        $('#reply-save').on('click',function () {
            _this.reply_save();
        });
        $('#reply-delete').on('click',function () {
            _this.reply_delete();
        });
    },

    save : function () {
      var data = {  // 제이쿼리 폼데이터를 받아준다
          title  : $('#title').val(),
          author : $('#author').val(),
          content : $('#content').val()
      };

      $.ajax({
          type : 'POST',
          url : '/blog/posts',
          dataType : 'json',
          contentType :'application/json ; charset=utf-8',
          data : JSON.stringify(data)   // js 객체를 JSON으로 변환하는 과정 @RequestBody로 넣어줌
      }).done(function () { // 성공시 실행함수
        alert("글이 등록되었습니다.");
        window.location.href ="/";
      }).fail(function (error) {
          alert(JSON.stringify(error));
      });
    },

    update : function () {
        var data ={
            title : $('#title').val(),
            content : $('#content').val(),
        };
        var id = $('#id').val();
        $.ajax({
            type : 'PUT',
            url : '/blog/post/'+id,
            dataType: 'text',
            contentType :'application/json ; charset=utf-8',
            data : JSON.stringify(data)
        }).done(function () {
            alert("수정 되었습니다");
            window.location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error))
        });
    },

    delete : function () {
        var id = $('#id').val();
        $.ajax({
            type : 'DELETE',
            url : '/blog/post/'+id,
            dataType : 'text',
            contentType :'application/json ; charset=utf-8',
            data : JSON.stringify(id)
        }).done(function () {
            alert("삭제 됐습니다");
            window.location.href ="/";
        }).fail(function (error) {
            alert(JSON.stringify(error))
        });
    },

    reply_save : function () {
        var data = {
            reply_name : $('#reply_author').val(),
            reply_content : $('#reply_content').val(),
            id : $('#id').val(),
        };
        var self = this;
        $.ajax({
            type : 'POST',
            url : '/reply/save',
            dataType : 'text',
            contentType :'application/json ; charset=utf-8',
            data : JSON.stringify(data)
        }).done(function () {
            alert("댓글이 등록됐습니다");
            self.reply_loading(data.id);
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    reply_loading : function (id) {
        $.ajax({
            type: 'GET',
            url: '/blog/' + id
        }).done(function () {
            window.location.reload();
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    },

    reply_delete : function () {
        var reply_id = $('#reply_id').val();
        var post_id = $('#id').val();
        $.ajax({
            type : 'DELETE',
            url : '/reply/'+post_id+'/'+reply_id,
        }).done(function () {
            alert("댓글이 삭제 됐습니다");
            window.location.reload();
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    },
};
main.init();