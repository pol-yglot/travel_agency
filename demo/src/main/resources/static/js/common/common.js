$(document).ready(function () {
    $('#logoutBtn').click(function (e) {
        e.preventDefault();
        $('#logoutForm').submit();
    });
});