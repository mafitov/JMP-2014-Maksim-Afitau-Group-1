
"use strict";
EPAM.BookMarkIndexView = Backbone.View.extend({

  initialize: function (options) {
    this.bookmarks = options.bookmarks;
    this.bookmarks.bind('reset', this.addAll, this);
  },

  render: function () {
    this.$el.html($('#bookmarkList').html());
    this.addAll();
    return this;
  },

  addAll: function () {
      _.each(this.bookmarks.models, $.proxy(this, 'addOne'));
  },

  addOne: function (bookmark) {
    var view = new EPAM.BookmarkRowView({
        bookmarks: this.bookmarks,
        bookmark: bookmark
    });
    this.$el.append(view.render().el);
  }
});

