/**
 * Created by lafickens on 7/3/15.
 */

function retrieveAsync() {
    var url = AJS.contextPath() + "/rest/jira-wechat/1.0/configuration";
    $.ajax({
        url: url,
        dataType: "json"
    }).done(function(config) {
        $("#corpId").val(config.corpId);
        $("#corpSecret").val(config.corpSecret);
    });
}

function updateAsync() {
    var url = AJS.contextPath() + "/rest/jira-wechat/1.0/";
    var corpId = $('#corpId').attr('value');
    var corpSecret = $('#corpSecret').attr('value');
    $.ajax({
        url: url,
        type: 'PUT',
        dataType: "json",
        data: '{"corpId": "' + corpId + '", corpSecret: "' + corpSecret + '"}',
        processData: false
    });
}

(function($) {
    $(document).ready(function() {
        retrieveAsync();
        $('#configSecret').submit(function(e) {
            e.preventDefault();
            updateAsync();
        })
    });
})(AJS.$ || jQuery);