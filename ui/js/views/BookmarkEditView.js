EPAM.BookmarkEditView = Backbone.View.extend({
    el: "#form",
    events: {
        "click button.save": "save",
        "click button#btnCancel": "destroy"
    },
    initialize: function (options) {
        this.bookmark  = options.bookmark;
        this.bookmark.bind('invalid', this.showErrors, this);
        this.$el.css("display", "block");
        this.render();
    },
    showErrors: function (bookmark, errors) {
        this.$el.find('.error').removeClass('error');
        this.$el.find('.alert').html(_.values(errors).join('<br>')).show();
        _.each(_.keys(errors), _.bind(function (key) {
            this.$el.find('*[name=' + key + ']').parent().addClass('error');
        }, this));
    },
    save: function (event) {
        event.stopPropagation();
        event.preventDefault();
        var oldBookmark = this.bookmark;
        this.bookmark.set({
            title: this.$el.find('input[id=title]').val(),
            urlRoot: this.$el.find('input[id=url]').val()
        });
        var tags = this.$el.find('input[id=tags]').val().split(",");
        var tagCollection = new EPAM.TagCollection();
        _.each(tags, function(tag) {
            tagCollection.add(new EPAM.TagModel({name: tag}));
        });
        this.bookmark.set({tags: tagCollection});
        if (this.bookmark.isValid()){
            this.destroy();
        } else {
            this.bookmark = oldBookmark;
        }
    },
    clear: function() {
        this.$el.find('input[id=title]').val("");
        this.$el.find('input[id=url]').val("");
        this.$el.find('input[id=tags]').val("");
    },
    render: function () {
        this.$el.find('input[id=title]').val(this.bookmark.attributes.title);
        this.$el.find('input[id=url]').val(this.bookmark.attributes.urlRoot);
        var tags = "";
        _.each(this.bookmark.attributes.tags.models, function(t, index, collection) {
            tags += t.attributes.name;
            if (index != collection.length - 1) {
                tags += ", ";
            }
        });
        this.$el.find('input[id=tags]').val(tags);
        return this;
    },
    destroy: function() {
        this.clear();
        this.$el.css("display", "none");
        window.location.hash = "index";
    }
});