$(function () {
    $('#solve_quadratic_equation_button').click(function () {
        var data = JSON.stringify(getFormData($('#solve_quadratic_equation form')))
        $.ajax({
            method: "POST",
            url: '/solve_quadratic_equation/',
            data: data,
            contentType: 'application/json',
            dataType: 'json',
            success: function (response) {
                $('#x1').text(response.x1)
                $('#x2').text(response.x2)
            },
            error: function (response) {
                if (response.status === 400) {
                    $('#x1').text('Нет решения')
                    $('#x2').text('Нет решения')
                }
            }
        });
        return false;
    });

    function getFormData($form){
        const un_indexed_array = $form.serializeArray();
        const indexed_array = {};

        $.map(un_indexed_array, function(n, i){
            indexed_array[n['name']] = n['value'];
        });

        return indexed_array;
    }
});
