$("#user-edit-modal").on('show.bs.modal', function(e) {
    const userId = $(e.relatedTarget).data('user-id');

    const cols = $('#user-' + userId + ' td');
    const enabled = $(cols[0]).text();
    const accountNonExpired = $(cols[1]).text();
    const accountNonLocked = $(cols[2]).text();
    const credentialsNonExpired = $(cols[3]).text();
    const id = $(cols[4]).text();
    const roles = $(cols[5]).text().replace(/\s+/g," ");
    const login = $(cols[6]).text();
    const password = '';
    const email = $(cols[8]).text();
    const phone = $(cols[9]).text();
    const bDate = $(cols[10]).text();


    $('#enabled-for-edit').val(enabled);
    $('#accountNonExpired-for-edit').val(accountNonExpired);
    $('#accountNonLocked-for-edit').val(accountNonLocked);
    $('#credentialsNonExpired-for-edit').val(credentialsNonExpired);
    $('#id-for-edit').val(id);
    $('#id-for-view').val(id);
    $('#login-for-edit').val(login);
    $('#password-for-edit').val(password);
    $('#email-for-edit').val(email);
    $('#phone-for-edit').val(phone);
    $('#birth_date-for-edit').val(bDate);

    printRoles(roles.split(' '));
});

function printRoles(arr) {
    $.getJSON("/admin/all-roles", {
        ajax : 'true'
    }, function(data) {
        let select = document.getElementById('roles-for-edit');
        const len = data.length;
        for (let i = 0; i < len; i++) {
            select[i] = new Option(data[i].role, data[i].id, false, arr.includes(data[i].role, 0));
        }
    });
}