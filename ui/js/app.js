window.EPAM = window.EPAM || {};
EPAM.NoteRouter = Backbone.Router.extend({
    initialize: function (options) {
        this.bookmarks = options.bookmarks;
    },
    routes: {
        "index": "index",
        "new": "create",
        "note/:id/edit": "edit",
        "note/:id/view": "show",
        ".*": "index"
    },

    edit: function(id) {
        var bookmark = this.bookmarks.get(id);
        alert(bookmark.cid);
        this.currentView = new EPAM.BookmarkEditView({
            bookmark: bookmark
        });
    } ,

    create: function () {
        this.currentView = new EPAM.BookmarkSaveView({
            bookmarks: this.bookmarks
        });
    },

    index: function () {
        this.currentView = new EPAM.BookMarkIndexView({
            bookmarks: this.bookmarks
        });
        $('#bookmarkListWrapper').html(this.currentView.render().el);
    }
});
