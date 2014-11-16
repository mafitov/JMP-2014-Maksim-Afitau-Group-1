
EPAM.Bookmark = Backbone.Model.extend({
    defaults: {
        title: "",
        urlRoot: "",
        tags: EPAM.TagCollection
    },
    validate: function (attrs) {
        var errors = {};
        if (!attrs.title) errors.title = "Hey! Give this thing a title.";
        if (!attrs.urlRoot) errors.urlRoot = "You gotta write an URL, duh!";
        if (!attrs.tags) errors.tags = "Put here some tags separated with comma...";

        if (!_.isEmpty(errors)) {
            return errors;
        }
    }
});

EPAM.BookmarkCollection = Backbone.Collection.extend({
    model: EPAM.Bookmark
});

EPAM.TagModel = Backbone.Model.extend({
    defaults: {
        name: ""
    }
});

EPAM.TagCollection = Backbone.Collection.extend({
    model: EPAM.TagModel
});
