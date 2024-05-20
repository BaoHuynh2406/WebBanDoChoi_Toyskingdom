class ProductCard {
    constructor(productData) {
        this.productData = productData;
    }

    render() {
        // Tạo HTML cho card sản phẩm từ dữ liệu sản phẩm
        const htmlContent = `
            <span class="badge badge__left">New</span>
            <span class="badge badge__right">-25%</span>
            <img src="assets/image/${this.productData.image}" class="product__img card-img-top"
                 alt="..." style="border-radius: 1.5rem;">
            <div class="card-body">
                <h5 class="card-title">${this.productData.productName}</h5>
                <p class="card-text text-danger fw-bold">${this.productData.price} Đ</p>

                <div class="row">
                    <div class="col-9">
                        <a href="#" class="w-100 btn btn_add_to_card">Thêm vào giỏ</a>
                    </div>

                    <div class="col-3">
                        <button class="far fa-heart btn_yeu_thich"></button>
                    </div>
                </div>
            </div>
        `;
        return htmlContent;
    }
}

export default ProductCard;
