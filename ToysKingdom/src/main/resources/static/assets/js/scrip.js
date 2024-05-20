
import ProductCard from './element/card.js';
class ProductLoader {
    products = [];

    loadInit = async () => { //xử lý bất đồng bộ
        await this.getData()
    }

    getData = async () => {
        let {data: response} = await axios.get('http://localhost:8080/api-public/products/getAllActiveProducts')
        this.products = response.data.map(e => {
            return {
                idProduct: e.idProduct,
                idCategory: e.idCategory,
                productName: e.productName,
                des: e.des,
                price: e.price,
                image: e.image,
                quantity: e.quantity,
                active: e.active
            }
        })
        this.products.forEach(function(productData) {
            const productCard = new ProductCard(productData);
            const htmlContent = productCard.render();
            $('#cardContainer').append(htmlContent);
        });
    }
}