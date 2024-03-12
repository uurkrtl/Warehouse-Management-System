import {useEffect, useState} from "react";
import {Product} from "../../types/Product.ts";
import ProductService from "../../services/ProductService.ts";
import {Link} from "react-router-dom";


function ProductList() {
    const [products, setProducts] = useState<Product[]>([]);
    const [filter, setFilter] = useState("");

    const filteredProducts = products.filter(
        (product) =>
            product.name.toLowerCase().includes(filter.toLowerCase())
    );

    useEffect(() => {
        const productService = new ProductService();
        productService.getAllProducts().then((response) => {
            setProducts(response.data);
        });
    });

    return (
        <div>
            <div className="py-5 text-center">
                <svg xmlns="http://www.w3.org/2000/svg" width="72" height="72" fill="currentColor"
                     className="bi bi-handbag" viewBox="0 0 16 16">
                    <path
                        d="M8 1a2 2 0 0 1 2 2v2H6V3a2 2 0 0 1 2-2m3 4V3a3 3 0 1 0-6 0v2H3.36a1.5 1.5 0 0 0-1.483 1.277L.85 13.13A2.5 2.5 0 0 0 3.322 16h9.355a2.5 2.5 0 0 0 2.473-2.87l-1.028-6.853A1.5 1.5 0 0 0 12.64 5zm-1 1v1.5a.5.5 0 0 0 1 0V6h1.639a.5.5 0 0 1 .494.426l1.028 6.851A1.5 1.5 0 0 1 12.678 15H3.322a1.5 1.5 0 0 1-1.483-1.723l1.028-6.851A.5.5 0 0 1 3.36 6H5v1.5a.5.5 0 1 0 1 0V6z"/>
                </svg>
                <h2>Produktliste</h2>
            </div>

            <div className="input-group">
                <span className="input-group-text" id="basic-addon3">Schreiben einen Namensfilter</span>
                <input
                    type="text"
                    className="form-control"
                    id="basic-url"
                    aria-describedby="basic-addon3 basic-addon4"
                    onChange={(e) => setFilter(e.target.value)}
                />
            </div>

            <table className="table table-striped">
            <thead>
                <tr>
                    <th scope="col">Name</th>
                    <th scope="col">Sale Price</th>
                    <th scope="col">Kategoriename</th>
                    <th scope="col">Status</th>
                    <th scope="col">Detail</th>
                    <th scope="col">Kaufhistorie</th>
                    <th scope="col">Verkaufsübersicht</th>
                </tr>
                </thead>
                <tbody>
                {filteredProducts.map((product) => {
                    return (
                        <tr key={product.id}>
                            <td>{product.name}</td>
                            <td>{product.salePrice}</td>
                            <td>{product.categoryName}</td>
                            <td>
                                {product.active ?
                                    <span className="badge text-bg-success rounded-pill">Aktiv</span>
                                    : <span className="badge text-bg-danger rounded-pill">Passiv</span>}
                            </td>
                            <td><Link to={"/"} className="btn btn-outline-info">Detail</Link></td>
                            <td><Link to={"/"} className="btn btn-outline-secondary">Kaufhistorie</Link>
                            </td>
                            <td><Link to={"/"} className="btn btn-outline-success">Verkaufsübersicht</Link>
                            </td>
                        </tr>
                    );
                })}
                </tbody>
            </table>
        </div>
    );
}

export default ProductList;