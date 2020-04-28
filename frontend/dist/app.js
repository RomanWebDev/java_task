Vue.component('tasks',{
    props: ["data"],
    methods:{
        task_delete() {
            this.$emit('task_delete');
        },
        handleChange(e) {
            this.$emit('task_done');
        }
    },
    template: `
        <div class="tile tile-centered" >
            <div class="tile-icon">
                <div class="example-tile-icon">
                <label class="form-checkbox">
                    <input v-model="data.is_done" type="checkbox" @change="handleChange($event)">
                    <i class="form-icon"></i>
                </label>
                </div>
            </div>
            <div class="tile-content">
            <div class="tile-title">{{data.title}}</div>
            {{data.description}}
            <small class="tile-subtitle text-gray">{{data.date}}</small>
            </div>
            <div class="tile-action">
            <button @click="task_delete()" class="btn btn-link">
                <i class="icon icon-cross"></i>
            </button>
            </div> 
        </div>
    `
})

var vue = new Vue({
    el: '#app',
    data: {
        api_url: "http://localhost:8080/api/todo/",
        tasks: [],
        title:null,
        description: null

    },
    mounted() {
        axios
          .get(this.api_url)
          .then(response => (this.tasks = response.data));

    },
    methods:{
        done_all(){
            for(i = 0;i < this.tasks.length; i++){
                this.tasks[i].is_done = true;
                this.task_update(this.tasks[i]);
            }
        },
        delete_all(){
            axios
                .delete(this.api_url)
                .then(response => (this.tasks = []));
        },
        delete_task(id, index)
        {
            axios
                .delete(this.api_url+id)
                .then(response => (this.tasks.splice(index,1)));
        },
        task_add() {
            axios
                .post(this.api_url, {
                    title: this.title,
                    description: this.description,
                    done: false
                })
                .then(response => (this.tasks.push(response.data)));
            this.title = null;
            this.description = null;
        },
        task_update(data) {
            axios
            .put(this.api_url+data.id, {
                id: data.id,
                title: data.title,
                description: data.description,
                is_done: data.is_done
            })
        }
    }
})