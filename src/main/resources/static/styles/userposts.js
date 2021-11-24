var clickedid;

function reply_click(clicked_id) {
    console.log(clicked_id)
    clickedid = clicked_id;
    return clicked_id;
}

function getModal() {
    var postId = clickedid.substr(6);
    $(clickedid).on('show.bs.modal', function () {
        $.get("/modals/profile/posts/delete/"+postId, function (data) {
            $(clickedid).find('.modal-div').html(data);
        })
    });
}


