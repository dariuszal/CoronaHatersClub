var clickedid;

function reply_click(clicked_id) {
    clickedid = clicked_id;
}

function getModal() {
    var postId = clickedid.substr(6);
    $(clickedid).on('show.bs.modal', function (event) {
        $.get("/modals/posts/delete/"+postId, function (data) {
            $(clickedid).find('.modal-div').html(data);
            $(clickedid).modal('toggle');
        })
    });
}