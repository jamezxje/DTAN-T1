let deleteImagesUrl = [];

$(document).ready(function () {
    previewImages();
    addRequiredFields();
    addOldImageDeleteEvent()

    $(".rateyo-readonly-widg").rateYo({
        starWidth: "32px",
        normalFill: "#A0A0A0",
        ratedFill: "#FFD700",
        numStars: 5,
        fullStar: true,
        readOnly: true
    });

    $('.logo-input').change(function () {
        previewImage(this);
    });

    $('.logo-input').change(function () {
        previewBrandImage(this);
    });


    $(document).on('click', '.delete-button', function (event) {
        event.preventDefault();
        var deleteFlag = $('#deleteFeatureImage');
        console.log('Before setting deleteFeatureImage:', deleteFlag.val());

        $('#images').val('');
        $('#imagesPreview').html('');
        $('.brand-logo-preview').attr('src', '').hide();
        $('.delete-button').hide();

        deleteFlag.val('true');
        console.log('After setting deleteFeatureImage:', deleteFlag.val());
    });

    function previewImage(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            var previewImageContainer = $(input).closest('.col-6').find('.brand-logo-container');
            previewImageContainer.find('.brand-logo-preview, .form-image-preview').remove();
            reader.onload = function (e) {
                var previewImage = $('<img>', {
                    src: e.target.result,
                    class: 'brand-logo-preview img-fluid',
                    alt: 'Brand Logo Preview',
                    style: 'height: 150px;'
                });
                var deleteButton = $('<button>', {
                    class: 'btn position-absolute transparent-bg delete-button',
                    style: 'top: 2px; right: 2px; color: red; font-weight: bold',
                    text: 'x'
                });
                previewImageContainer.append(previewImage);
                previewImageContainer.append(deleteButton);
            }

            reader.readAsDataURL(input.files[0]);
        } else {
            $(input).closest('.col-6').find('.brand-logo-preview, .delete-button').remove();
            var deleteFlag = $('#deleteFeatureImage');
            $('#images').val('');
            $('#imagesPreview').html('');
            $('.brand-logo-preview').attr('src', '').hide();
            $('.delete-button').hide();
            deleteFlag.val('true');
        }
    }

    function previewBrandImage(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            var previewImageContainer = $(input).closest('.form-group').find('.brand-logo-container');
            previewImageContainer.find('.brand-logo-preview, .form-image-preview').remove();
            reader.onload = function (e) {
                var previewImage = $('<img>', {
                    src: e.target.result,
                    class: 'brand-logo-preview img-fluid',
                    alt: 'Brand Logo Preview',
                    style: 'height: 150px;'
                });
                var deleteButton = $('<button>', {
                    class: 'btn position-absolute transparent-bg delete-button',
                    style: 'top: 2px; right: 2px; color: red; font-weight: bold',
                    text: 'x'
                });
                previewImageContainer.append(previewImage);
                previewImageContainer.append(deleteButton);
            }

            reader.readAsDataURL(input.files[0]);
        } else {
            $(input).closest('.form-group').find('.brand-logo-preview, .delete-button').remove();
            var deleteFlag = $('#deleteFeatureImage');
            $('#images').val('');
            $('#imagesPreview').html('');
            $('.brand-logo-preview').attr('src', '').hide();
            $('.delete-button').hide();
            deleteFlag.val('true');
        }
    }

    $('.image-input').change(function () {
        previewMemberImage(this);
    });

    $(document).on('click', '.img-delete-button', function (event) {
        event.preventDefault();
        var deleteFlag = $('#deleteFeatureImage');
        console.log('Before setting deleteFeatureImage:', deleteFlag.val());

        $('#images').val('');
        $('#imagesPreview').html('');
        $('.member-image-preview').hide();
        $('.img-delete-button').hide();

        deleteFlag.val('true');
        console.log('After setting deleteFeatureImage:', deleteFlag.val());
    });

    function previewMemberImage(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            var previewImageContainer = $(input).closest('.form-group').find('.member-image-container');
            previewImageContainer.find('.member-image-preview, .form-image-preview').remove();
            reader.onload = function (e) {
                var previewImage = $('<img>', {
                    src: e.target.result,
                    class: 'member-image-preview img-fluid',
                    alt: 'Member Image Preview',
                    style: 'height: 150px;'
                });
                var deleteButton = $('<button>', {
                    class: 'btn position-absolute transparent-bg img-delete-button',
                    style: 'top: 2px; right: 2px; color: red; font-weight: bold',
                    text: 'x'
                });
                previewImageContainer.append(previewImage);
                previewImageContainer.append(deleteButton);
            }

            reader.readAsDataURL(input.files[0]);
        } else {
            $(input).closest('.form-group').find('.member-image-preview, .img-delete-button').remove();
            var deleteFlag = $('#deleteFeatureImage');
            $('#images').val('');
            $('#imagesPreview').html('');
            $('.member-image-preview').attr('src', '').hide();
            $('.img-delete-button').hide();
            deleteFlag.val('true');
        }
    }
});

function clearImagesInput() {
    $('#images').val('');
    $('#imagesPreview').html('');
}

function previewImages() {
    $('#featureImage').on('change', function () {
        $('#featureImagePreview').html('');
        var file = $(this)[0].files[0];
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#featureImagePreview').html('<img src="' + e.target.result + '" class="img-fluid" style="height: 80px"/>')
        }
        reader.readAsDataURL(file);
    });
    $('#images').on('change', function () {
        bindImages();
    });
}

function addRequiredFields() {
    if ($('#carId').val() === '') {
        $('#featureImage').attr('required', 'required');
        $('#images').attr('required', 'required');
    }
}

function addOldImageDeleteEvent() {
    $('.image-delete-btn').on('click', function () {
        //get data-id of the clicked button
        let url = $(this).data('image-url');
        if (url) {
            deleteImagesUrl.push(url);
            bindDeleteImagesInput();
            $(this).parent().remove();
        }
    });
}

function bindImages() {
    $('#imagesPreview').html('');
    let files = $('#images')[0].files;
    let images = '';
    for (let i = 0; i < files.length; i++) {
        images += `
                    <img src="${URL.createObjectURL(files[i])}" class="img-fluid d-block m-2"
                         style="height: 60px" alt="Gallery Image">
            `
    }
    $('#imagesPreview').append(images);
}

function bindDeleteImagesInput() {
    $('#deleteImagesUrl').html('');
    let html = '';
    deleteImagesUrl.forEach(function (url, index) {
        html += `<input type="hidden" name="deleteImagesUrl[${index}]" value="${url}">`;
    });
    $('#deleteImagesUrl').html(html);
}