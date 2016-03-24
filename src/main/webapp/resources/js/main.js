/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
'use strict';

//Make sure jQuery has been loaded before app.js
if (typeof jQuery === "undefined") {
    throw new Error("Websocket-notification requires jQuery");
}

// Template parser
var parseTemplate = function (template, data) {
    return template.replace(/\{([\w\.]*)\}/g, function (str, key) {
        var keys = key.split("."), v = data[keys.shift()];
        for (var i = 0, l = keys.length; i < l; i++)
            v = v[keys[i]];
        return (typeof v !== "undefined" && v !== null) ? v : "";
    });
};

var setiarFecha = function () {
    
    $('#content [data-daterange]').each(function () {
        var element = $(this);
        element.daterangepicker();
        element.keydown(function () {
            return false;
        });
    });
    $('#content [data-daterangetime]').each(function () {
        var element = $(this);
        element.daterangepicker({
            timePicker: true,
            timePickerIncrement: 10,
            locale: {
                format: 'DD/MM/YYYY hh:mm A'
            }
        });
        element.keydown(function () {
            return false;
        });
    });
    $('#content [data-datepicker]').each(function () {
        var element = $(this);
        element.datetimepicker({autoclose: true});
    });
};
$(document).ready(function () {
    setiarFecha();
});
// Global initialization for standard JSF ajax API.
if (typeof jsf !== "undefined") {
    jsf.ajax.addOnEvent(function (data) {
        if (data.status === "success") {
            setiarFecha();
        }
    });
}
