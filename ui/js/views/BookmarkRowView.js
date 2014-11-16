EPAM.BookmarkRowView = Backbone.View.extend({
    tagName: "li",
    events: {
    "click a.delete": "destroy"
    },
    initialize: function (options) {
        this.bookmark  = options.bookmark;
        this.bookmarks = options.bookmarks;
    },

    render: function () {
        this.$el.append(_.template($('#rowTemplate').html(), this.bookmark.toJSON()));
        this.renderTag(this.bookmark.attributes.tags.models);
        return this;
    },

     renderTag: function(tags) {
         var tagView = new EPAM.TagView({tags: tags});
         this.$el.find(".bookmark-tags").append(tagView.render().el);
     },

    destroy: function (event) {
        event.preventDefault();
        event.stopPropagation();
        this.bookmarks.remove(this.bookmark);
        this.$el.remove();
    }
});

EPAM.TagView = Backbone.View.extend({
    tagName: "span",
    initialize: function (options) {
        this.tags = options.tags;
    },
    render: function() {
        for(var i = 0; i < this.tags.length; i++ ){
//            this.$el.append(_.template($('#tagTemplate').html(), this.tags[i].toJSON()));
            this.$el.append(this.tags[i].attributes.name);
            if (i != this.tags.length - 1) {
                this.$el.append(",");
            }
        }
        return this;
    }
});
