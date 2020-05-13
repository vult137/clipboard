<template>
    <div id="app">
        <div id="top-bar">
            <div id="label">CLIP_BOARD</div>
            <div id="about">About</div>
        </div>
        <div id="left-bar">
            <div v-on:click="createNew" class="left-bar-block">NEW</div>
            <div v-for="ele in content_list" v-bind:key="ele.id" v-on:click="changeText(ele)" class="left-bar-block">
                <p>{{ele.title}}</p>
                <button style="float: right" v-on:click="delete_content(ele.id)">delete</button>
            </div>
        </div>
        <div id="text-div">
            <label>
                <input id="text-area-title" v-model="title" autocomplete="off"/>
            </label>
            <div id="text-area-date">
                last update time: {{this.updateTime | date_prettify}}
            </div>
            <label>
                <textarea id="text-area-content" v-model="text"></textarea>
            </label>
        </div>
        <div id="button-area">
            <div style="width: 30%"></div>
            <button v-on:click="cancel">CANCEL</button>
            <button v-on:click="submit">SUBMIT</button>
        </div>
    </div>
</template>

<script>
    import {getPageOrderByUpdateTime, createContent,
        updateContent, deleteContent} from "@/api/content";

    export default {
        name: 'App',
        data() {
            return {
                title: "",
                text: "",
                updateTime: null,
                creating: false,
                content_list: null,
                cur_content: null
            }
        },
        mounted() {
            getPageOrderByUpdateTime(0, 10).then(r => {
                this.content_list = r.data;
                this.cur_content = r.data[0];
                this.title = r.data[0].title;
                this.text = r.data[0].context;
                this.updateTime = r.data[0].updateTime;
            })
        },
        methods: {
            changeText(ele) {
                this.cur_content = ele;
                this.title = ele.title;
                this.text = ele.context;
                this.updateTime = ele.updateTime;
                this.creating = false;
            },
            createNew() {
                this.title = "";
                this.text = "";
                this.updateTime = "";
                this.creating = true;
            },
            cancel() {
                if (this.creating === true) {
                    this.title = "";
                    this.text = "";
                } else {
                    this.title = this.cur_content.title;
                    this.text = this.cur_content.context;
                }
            },
            submit() {
                if (this.creating === true) {
                    createContent(this.title, this.text);
                    location.reload();

                } else {
                    updateContent(this.cur_content.id, this.title, this.text);
                    location.reload();
                }
            },
            delete_content(id) {
                deleteContent(id);
                location.reload();
            }
        },
        filters: {
            date_prettify(raw_date) {
                if (raw_date === "" || raw_date === undefined || raw_date === null)
                    return raw_date;
                raw_date = raw_date.replace(/-/g,"/");
                raw_date = raw_date.replace(/(\.\d+)?/g,"");
                raw_date = raw_date.replace("T", " ");
                raw_date = raw_date.replace(/\+\d+/g, "");
                return raw_date;
            }
        }
    }
</script>

<style>
    html, body {
        height: 100%;
    }
    #app {
        font-family: Avenir, Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
        /*margin-top: 60px;*/
    }
    #top-bar {
        position: absolute;
        float: top;
        margin-left: 4%;
        height: 50px;
        width: 92%;
        border: black 2px solid;
    }
    #label {
        float: left;
        height: 100%;
        width: 16.1%;
        font-size: xx-large;
        text-align: center;
        border-right: black 1px solid;
    }
    #about {
        position: relative;
        float: right;
    }
    #left-bar {
        width: 15%;
        float: left;
        height: 650px;
        margin-left: 4%;
        margin-top: 80px;
        border: 2px solid #000000;
        overflow: scroll;
    }
    .left-bar-block {
        width: 100%;
        height: 50px;
        float: top;
        border-bottom: 1px solid red;
    }
    #text-div {
        float: left;
        width: 76.25%;
        height: 650px;
        margin-left: 1.5%;
        margin-top: 80px;
    }
    #text-area-title {
        width: 100%;
        font-size: xx-large;
    }
    #text-area-date {
        text-align: left;
        margin: 8px;
    }
    #text-area-content {
        width: 100%;
        height: 88%;
        font-size: large;
    }
    #button-area {
        margin: 30px;
        width: 100%;
        float: left;
    }

</style>
