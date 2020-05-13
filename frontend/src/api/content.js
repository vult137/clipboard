import axios from 'axios'

export async function getPageOrderByUpdateTime(start, offset) {
    const order = 2;
    var res = null;
    await axios.get("/apis/content", {
        params: {
                start: start,
                offset: offset,
                order: order
            }
        }).then(r => {
            res = r.data;
    }).catch(error => {
        console.log(error)
    });
    return res
}

export async function createContent(title, context) {
    var res = null;
    const data = {
        title: title,
        context: context
    };
    await axios.post(
        "/apis/content",
        data
    ).then(r => {
        res = r;
    }).catch(error => {
        console.log(error);
    });
    return res;
}

export async function updateContent(id, title, context) {
    var res = null;
    const data = {
        title: title,
        context: context
    };
    await axios.put(
        "/apis/content/" + id,
        data
    ).then(r => {
        res = r
    }).catch(error => {
        console.log(error)
    });
    return res;
}
export async function deleteContent(id) {
    var res = null;
    await axios.delete(
        "/apis/content/" + id
    ).then(r => {
        res = r;
    }).catch(error => {
        console.log(error);
    });
    return res;
}