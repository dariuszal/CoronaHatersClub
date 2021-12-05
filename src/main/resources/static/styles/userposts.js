var clickedid;

function reply_click(clicked_id) {
    clickedid = clicked_id;
    return clicked_id;
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
 // function test() {
 //     fetch('http://localhost:8080/modals/profile/posts').then(function (response) {
 //         // The API call was successful!
 //         return response.text();
 //     }).then(function (html) {
 //         // This is the HTML from our response as a text string
 //         console.log(html);
 //     }).catch(function (err) {
 //         // There was an error
 //         console.warn('Something went wrong.', err);
 //     });
 // }


