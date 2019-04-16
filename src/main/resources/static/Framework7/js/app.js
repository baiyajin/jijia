var $$ = Dom7;
var theme = 'auto';


// console.log(Framework7.device);
if(Framework7.device.ios){
    theme = 'ios';
    /*ios*/
    jQuery("#fab-right-top-a").append(
        "<i class=\"icon f7-icons ios-only\">add</i>\n" +
        "<i class=\"icon f7-icons ios-only\">close</i>"
    );
}else {
    theme = 'md';
    /*安卓*/
    jQuery("#fab-right-top-a").append(
        "<i class=\"icon material-icons md-only\">add</i>\n" +
        "<i class=\"icon material-icons md-only\">close</i>"
    );
}
/*theme = 'ios';
jQuery("#fab-right-top-a").append(
    "<i class=\"icon f7-icons ios-only\">add</i>\n" +
    "<i class=\"icon f7-icons ios-only\">close</i>"
);*/
var app = new Framework7({
    id: 'io.framework7.testapp',
    root: '#app',
    theme: theme,
    data: function() {
        return {
            user: {
                firstName: 'John',
                lastName: 'Doe',
            },
        };
    },
    methods: {
        helloWorld: function() {
            app.dialog.alert('Hello World!');
        },
    },
    routes: routes,
    vi: {
        placementId: 'pltd4o7ibb9rc653x14',
    },
});
var toastBottom;





