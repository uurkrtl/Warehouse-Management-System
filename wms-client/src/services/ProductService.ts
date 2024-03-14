import axios from 'axios';
import {Product} from "../types/Product.ts";

export default class ProductService {
    getAllProducts() {
        return axios.get('/api/products');
    }

    getByIdProduct(id: string) {
        return axios.get('/api/products/' + id);
    }

    addProduct(product: Product) {
        return axios.post('/api/products/add', product);
    }

    updateProduct(id:string, product: Product) {
        return axios.put(`/api/products/update?id=${id}`,product);
    }

    makeStatusActive(id: string) {
        return axios.put(`/api/products/makeStatusActive?id=${id}`);
    }

    makeStatusPassive(id: string) {
        return axios.put(`/api/products/makeStatusPassive?id=${id}`);
    }

}