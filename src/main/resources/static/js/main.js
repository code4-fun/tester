var testApi = Vue.resource('/testWorks{/id}');

Vue.component('test-work', {
    props: ['test'],
    template: '<div><i>({{ test.id }}) </i>{{ test.name }} <b>{{ test.dateTime }}</b></div>'
});

Vue.component('test-work-list', {
    props: ['tests'],
    template: `<div><test-work v-for="test in tests" 
                    :key="test.id" 
                    :test="test" />
                </div>`,
    created: function () {
        testApi.get()
            .then(response => response.json()
                .then(data => data.forEach(test => this.tests.push(test)))
            )
    }
});

var app = new Vue({
    el: '#app',
    template: '<test-work-list :tests="tests" />',
    data:{
      tests: []
    }
});