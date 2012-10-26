$(document).ready(function() {
    $('.search').submit(function() {
      $.getJSON('./service/search?'+$(this).serialize(), function(data) {
        $('#searchHeader').text("Search Results");
        $('#searchResults').html('');
        addRestaurantListToPage(data);
      })
      return false;
    });
    $.getJSON('./service/main/hotshops', function(data) {
        addRestaurantListToPage(data);
    })        
});

function addRestaurantListToPage(data) {
    $.each(data, function(index, obj) {
        var div;
        div = $('<div />' , {
            class: 'whitewrapper'
        });

        $('<div />', {
            class: 'hd'
        }).appendTo(div);

        var attrList = new Array();
        var pre,mid,post;
        pre = "<li><label>";
        mid = "</label>\n<span>";
        post = "</span>";
        $.each(obj, function(key, val) {
            switch (key) {
                case "name":
                    attrList[0] = pre+"Name"+mid+val+post;
                    break;
                case "address":
                    attrList[1] = pre+"Address"+mid+val+post;
                    break;
                case "category":
                    attrList[2] = pre+"Category"+mid+val+post;
                    break;
                case "avgPrice":
                    attrList[3] = pre+"Average Price"+mid+val+post;
                    break;
                case "area":
                    attrList[4] = pre+"Area"+mid+val+post;
                    break;
                default:
                    break;
            }
        });

        var restList = $('<ul />');
        for (i=0;i<5;i++) {
            restList.html(restList.html() + attrList[i]);
        }

        restList.appendTo(div);
        div.appendTo($('#searchResults'));
    });
}