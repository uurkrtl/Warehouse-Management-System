import axios from "axios";

export default class CategoryService {
    getAllCategories() {
        return axios.get('/api/categories');
    }
}