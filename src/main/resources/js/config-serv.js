/**
 * Created by lafickens on 7/3/15.
 */

function updateAsync() {
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
    var url = AJS.contextPath() + "/rest/jira-wechat/1.0/";
    $(document).ready(function() {
        $('#configSecret').submit(function(e) {
            e.preventDefault();
            updateAsync();
        })
    });
})(AJS.$ || jQuery);