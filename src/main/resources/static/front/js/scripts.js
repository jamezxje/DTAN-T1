$(document).ready(function () {

    /**
     * Rating function
     */

    $("#rateYo").rateYo({
        starWidth: "32px",
        normalFill: "#A0A0A0",
        ratedFill: "#FFD700",
        numStars: 5,
        fullStar: true
    });

    $("#rateYo").on("rateyo.set", function (e, data) {
        console.log(data.rating);
        $('input:hidden[name="feedbackPoint"]').val(data.rating);
    })

    $("#rateyo-readonly-widg").rateYo({
        starWidth: "32px",
        normalFill: "#A0A0A0",
        ratedFill: "#FFD700",
        numStars: 5,
        fullStar: true,
        readOnly: true
    });

    $(".rateyo-readonly-widg").rateYo({
        starWidth: "32px",
        normalFill: "#A0A0A0",
        ratedFill: "#FFD700",
        numStars: 5,
        fullStar: true,
        readOnly: true
    });

    /**
     * Booking form function
     */

    $pickupTime = $('#pickup_time');
    $returnTime = $('#return_time');

    $basePrice = $('input:hidden[name="basePrice"]');

    $feePrice = $('input:hidden[name="feePrice"]');
    $totalPrice = $('input:hidden[name="totalPrice"]');
    $subTotalPrice = $('input:hidden[name="subTotalPrice"]');
    $totalDays = $('input:hidden[name="totalDays"]');

    $totalPriceTxt = $('#total-price');
    $subTotalPriceTxt = $('#sub-total-price');
    $feePriceTxt = $('#fee-price');
    $totalDaysTxt = $('#total-days');

    $pickupType = $('input[name="pickupType"]:checked');

    if ($pickupType.val() === undefined) {
        $pickupType = $('input[name="pickupType"]:hidden');
    }

    $feePrice.val($pickupType.data('price'));
    $feePriceTxt.text('$' + $pickupType.data('price'));

    $('input[name="pickupType"]').change(function () {
        var feePrice = $('input[name="pickupType"]:checked').data('price');
        $feePriceTxt.text('$' + feePrice);
        $feePrice.val(feePrice);

        var pickupTimeValue = $pickupTime.val();
        var returnTimeValue = $returnTime.val();

        console.log('pickup time: ' + pickupTimeValue);
        console.log('return time: ' + returnTimeValue);

        if (pickupTimeValue === '' || returnTimeValue === '' || new Date(pickupTimeValue) > new Date(returnTimeValue)) {
            return;
        }

        $totalPrice.val(parseInt($subTotalPrice.val()) + parseInt(feePrice));
        $totalPriceTxt.text('$' + parseInt(parseInt($subTotalPrice.val()) + parseInt(feePrice)));

        console.log('fee price:' + parseInt(feePrice));
        console.log('sub total: ' + $subTotalPrice.val())
        console.log('total: ' + parseInt($subTotalPrice.val()) + parseInt(feePrice));
    });


    $('#pickup_time, #return_time').on('change', function () {

        $('.pickup-time-error').html('');

        var pickupTimeValue = $pickupTime.val();
        var returnTimeValue = $returnTime.val();

        console.log('pickup time: ' + pickupTimeValue);
        console.log('return time: ' + returnTimeValue);

        if (pickupTimeValue === '' || returnTimeValue === '' || new Date(pickupTimeValue) >= new Date(returnTimeValue)) {
            $totalPrice.val(0);
            $subTotalPrice.val(0);
            $totalDays.val(0);

            $totalPriceTxt.text('---');
            $subTotalPriceTxt.text('---');
            $totalDaysTxt.text('---');

            if (new Date(pickupTimeValue) >= new Date(returnTimeValue)) {
                $('.pickup-time-error').html('<span class="text-danger"><i>Return time must after pickup time</i></span>')
            }

            return;
        }

        pickupTimeValue = new Date(pickupTimeValue);
        returnTimeValue = new Date(returnTimeValue);

        if (pickupTimeValue < new Date()) {
            $('.pickup-time-error').html('<span class="text-danger"><i>Pickup time must be in the future</i></span>');
            return;
        }

        var timeDiff = Math.abs(returnTimeValue.getTime() - pickupTimeValue.getTime());
        var daysDiff = Math.ceil(timeDiff / (1000 * 3600 * 24));

        if (daysDiff > 30) {
            $('.pickup-time-error').html('<span class="text-danger"><i>Maximum rental period is 30 days</i></span>');
            return;
        }

        $totalDays.val(daysDiff);
        $totalDaysTxt.text(' x ' + daysDiff + ' day(s) ');

        $subTotalPrice.val(parseInt(daysDiff * $basePrice.val()));
        $subTotalPriceTxt.text('$' + daysDiff * $basePrice.val());

        $totalPriceTxt.text('$' + parseInt(parseInt(daysDiff * $basePrice.val()) + parseInt($feePrice.val())));
        $totalPrice.val(parseInt(parseInt(daysDiff * $basePrice.val()) + parseInt($feePrice.val())));
    });

    $('#bookingForm').on('submit', function (e) {
        e.preventDefault();

        $this = $(this);

        $('.pickup-time-error').html('');

        var pickupTimeValue = $pickupTime.val();
        var returnTimeValue = $returnTime.val();

        if (pickupTimeValue === '' || returnTimeValue === '') {
            $('.pickup-time-error').html('<span class="text-danger"><i>You must enter both pickup time and return time</i></span>');
            return false;
        }

        var pickupTime = new Date(pickupTimeValue);
        var returnTime = new Date(returnTimeValue);

        if (pickupTimeValue < new Date()) {
            $('.pickup-time-error').html('<span class="text-danger"><i>Pickup time must be in the future</i></span>');
            return;
        }

        if (pickupTime >= returnTime) {
            $('.pickup-time-error').html('<span class="text-danger"><i>Return time must after pickup time</i></span>');
            return false;
        }

        // Calculate number of days between pickupTime and returnTime
        var timeDiff = Math.abs(returnTime.getTime() - pickupTime.getTime());
        var daysDiff = Math.ceil(timeDiff / (1000 * 3600 * 24));

        if (daysDiff > 30) {
            $('.pickup-time-error').html('<span class="text-danger"><i>Maximum rental period is 30 days</i></span>');
            return false;
        }

        $fullName = $('#fullName').val().replace(/[^a-zA-Z0-9,.\-_ ]/g, "");
        $phoneNumber = $('#phoneNumber').val().replace(/[^a-zA-Z0-9,.\-_ ]/g, "");
        $address = $('#address').val().replace(/[^a-zA-Z0-9,.\-_ ]/g, "");

        console.log('Full name: ' + $fullName);
        console.log('Phone number: ' + $phoneNumber);
        console.log('Address: ' + $address);

        if ($fullName === '' || $phoneNumber === '' || $address === '') {
            $('.billing-address-error').html('<span class="text-danger"><i>Billing address is required</i></span>');
            return false;
        }

        $pickupMethod = $('input[name="pickupType"]:checked').val().replace(/[^a-zA-Z0-9,.\-_ ]/g, "");
        $paymentMethod = $('input[name="paymentMethod"]:checked').val().replace(/[^a-zA-Z0-9,.\-_ ]/g, "");

        if ($pickupMethod === undefined) {
            $('.pickup-method-error').html('<span class="text-danger"><i>Please select pickup method and payment method</i></span>');
            return false;
        }

        if ($paymentMethod === undefined) {
            $('.payment-method-error').html('<span class="text-danger"><i>Please select payment method and payment method</i></span>');
            return false;
        }

        console.log($this.serialize());
        $this.unbind('submit').submit();

    });

    $('#min').on('input', function () {
        $('#minValue').html($(this).val());
    });
    $('#max').on('input', function () {
        $('#maxValue').html($(this).val());
    });

    /**
     * member profile function
     */

    $('.logo-input').change(function () {
        previewImage(this);
    });

    $(document).on('click', '.delete-button', function (event) {
        event.preventDefault();
        var deleteFlag = $('#deleteFeatureImage');
        console.log('Before setting deleteFeatureImage:', deleteFlag.val());

        $('#images').val('');
        $('#imagesPreview').html(''); // Clear the images preview container
        $('.brand-logo-preview').attr('src', '').hide();
        $('.delete-button').hide();
        deleteFlag.val('true');
        console.log('After setting deleteFeatureImage:', deleteFlag.val());
    });
});

function previewImage(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        var previewImageContainer = $(input).closest('.col-6').find('.brand-logo-container');
        previewImageContainer.find('.brand-logo-preview').remove();
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