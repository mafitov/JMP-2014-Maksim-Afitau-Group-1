EPAM.BookmarkSaveView = Backbone.View.extend({
    el: "#form",
    events: {
        "click button.save": "save",
        "click button#btnClear": "clear",
        "click button#btnCancel": "destroy"
    },
    initialize: function (options) {
        this.bookmark  = new EPAM.Bookmark();
        this.bookmarks = options.bookmarks;
        this.bookmark.bind('invalid', this.showErrors, this);
        this.$el.css("display", "block");
    },
    showErrors: function (bookmark, errors) {
        this.$el.find('.error').removeClass('error');
        this.$el.find('.alert').html(_.values(errors).join('<br>')).show();
        // highlight the fields with errors
        _.each(_.keys(errors), _.bind(function (key) {
            this.$el.find('*[name=' + key + ']').parent().addClass('error');
        }, this));
    },
    save: function (event) {
        event.stopPropagation();
        event.preventDefault();

        this.bookmark  = new EPAM.Bookmark();

        this.bookmark.set({
            title: this.$el.find('input[id=title]').val(),
            urlRoot: this.$el.find('input[id=url]').val(),
            id: Math.floor(Math.random() * 100) + 1
        });
        var tags = this.$el.find('input[id=tags]').val().split(",");
        var tagCollection = new EPAM.TagCollection();
        _.each(tags, function(tag) {
            tagCollection.add(new EPAM.TagModel({name: tag}));
        });
        this.bookmark.set({tags: tagCollection});
        if (this.bookmark.isValid()){
            this.bookmarks.add(this.bookmark);
            this.destroy();
        }
    },
    clear: function() {
        this.$el.find('input[id=title]').val("");
        this.$el.find('input[id=url]').val("");
        this.$el.find('input[id=tags]').val("");
    },
    render: function () {
        return this;
    },
    destroy: function() {
        this.clear();
        this.$el.css("display", "none");
        window.location.hash = "index";
    }
});