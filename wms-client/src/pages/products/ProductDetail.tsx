import {Link, useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {Product} from "../../types/Product.ts";
import ProductService from "../../services/ProductService.ts";

const productService = new ProductService();

function ProductDetail() {
    const { id = '' } = useParams<string>();
    const [product, setProduct] = useState<Product>({
        name: '',
        id: id,
        description: '',
        salePrice: 0,
        stock: 0,
        criticalStock: 0,
        imageUrl: '',
        categoryId: '',
        categoryName: '',
        createdAt: new Date(),
        updatedAt: new Date(),
        active: true
    });
    const navigate = useNavigate();
    const [errorMessage, setErrorMessage] = useState<string>('');

    useEffect(() => {
        if (id) {
            productService.getByIdProduct(id)
                .then((response) => {
                    setProduct(response.data);
                })
                .catch((error) => {
                    console.error('Error fetching product:', error);
                    navigate('*');
                });
        }
    }, [id, navigate]);

    const handleSubmitActive = (e: React.MouseEvent<HTMLButtonElement, MouseEvent>) => {
        e.preventDefault();
        if (id) {
            productService.makeStatusActive(id)
                .then(response => {
                    console.log(response)
                    window.location.reload();
                })
                .catch(error => {
                    if (error.response) {
                        console.log(error.response.data);
                        setErrorMessage(error.response.data.errorMessage);
                    } else {
                        console.log('Something went wrong:', error.message);
                        setErrorMessage('Something went wrong: ' + error.message);
                    }
                });
        } else {
            console.error('Product id is not defined');
        }
    };

    const handleSubmitPassive = (e: React.MouseEvent<HTMLButtonElement, MouseEvent>) => {
        e.preventDefault();
        if (id) {
            productService.makeStatusPassive(id)
                .then(response => {
                    console.log(response)
                    window.location.reload();
                })
                .catch(error => {
                    if (error.response) {
                        console.log(error.response.data);
                        setErrorMessage(error.response.data.errorMessage);
                    } else {
                        console.log('Something went wrong:', error.message);
                        setErrorMessage('Something went wrong: ' + error.message);
                    }
                });
        } else {
            console.error('Product id is not defined');
        }
    };

    return (
        <div className="row flex-lg-row-reverse align-items-center g-5 py-2">
            <div className="col-10 col-sm-8 col-lg-6">
                <img src={product.imageUrl} className="d-block mx-lg-auto img-fluid" alt="Product image"
                     width="400" height="300" loading="lazy"/>
            </div>
            <div className="col-lg-6">
                <h1 className="display-5 fw-bold text-body-emphasis lh-1 mb-3">{product.name}</h1>
                <p className="lead">{product.description}</p>

                <table className="table table-striped-columns">
                    <tbody>
                    <tr>
                        <th scope="row">Verkaufspreis</th>
                        <td>{product.salePrice}</td>
                    </tr>
                    <tr>
                        <th scope="row">Menge des Lagerbestands</th>
                        <td>{product.stock}</td>
                    </tr>
                    <tr>
                        <th scope="row">Kritischer Lagerbestand</th>
                        <td>{product.criticalStock}</td>
                    </tr>
                    <tr>
                        <th scope="row">Kategoriename</th>
                        <td>{product.categoryName}</td>
                    </tr>
                    <tr>
                        <th scope="row">Hergestellt am</th>
                        <td>{product.createdAt ? product.createdAt.toString() : "-"}</td>
                    </tr>
                    <tr>
                        <th scope="row">Aktualisiert am</th>
                        <td>{product.updatedAt ? product.updatedAt.toString() : "-"}</td>
                    </tr>
                    <tr>
                    <th scope="row">Status</th>
                        <td>{product.active ?
                            <span className="badge text-bg-success rounded-pill">Aktiv</span>
                            : <span className="badge text-bg-danger rounded-pill">Passiv</span>}</td>
                    </tr>
                    </tbody>
                </table>

                <div className="d-grid gap-2 d-md-flex justify-content-md-start">
                    <Link to={`/products/update/${product.id}`} type="button" className="btn btn-primary btn-lg px-4 me-md-2">Aktualisieren</Link>
                    {product.active ?
                        <button type="button" className="btn btn-danger px-4 me-md-2" onClick={handleSubmitPassive}>Passiv machen</button>
                        : <button type="button" className="btn btn-success px-4 me-md-2" onClick={handleSubmitActive}>Aktiv machen</button>}
                    <Link to={`/products`} type="button" className="btn btn-outline-secondary btn-lg px-4">Produktliste</Link>
                </div>
                {errorMessage && (
                    <div className="alert alert-danger mt-3" role="alert">
                        {errorMessage}
                    </div>
                )}
            </div>
        </div>
    );
}

export default ProductDetail;