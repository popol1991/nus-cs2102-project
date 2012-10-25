    $(document).ready(function() {
      $('.search').submit(function() {
        $.getJSON('./service/search?'+$(this).serialize(), function(data) {
          $('#searchHeader').text("Search Results");
          $('#searchResults').html('');
          $.each(data, function(index, obj) {
          var div,content;
          div = $('<div />' , {
            class: 'whitewrapper'
          });

          $('<div />', {
            class: 'hd'
          }).appendTo(div);

          $.each(obj, function(key, val) {
            content = content + key + ":" + val + "\n";
          });

          $('<p />').text(content).appendTo(div);
          div.appendTo($('#searchResults'));
          });
        })
        return false;
      });
      $.getJSON('./service/main/hotshops', function(data) {
        $.each(data, function(index, obj) {
          var div,content;
          div = $('<div />' , {
            class: 'whitewrapper'
          });

          $('<div />', {
            class: 'hd'
          }).appendTo(div);

          $.each(obj, function(key, val) {
            content = content + key + ":" + val + "\n";
          });

          $('<p />').text(content).appendTo(div);
          div.appendTo($('#searchResults'));
        });
      })        
    });