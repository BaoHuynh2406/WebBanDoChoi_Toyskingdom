<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>




            <style>
                @keyframes rotate {
                    0% {
                        transform: rotate(0deg);
                    }

                    100% {
                        transform: rotate(360deg);
                    }
                }

                .rotate-icon {
                    animation: rotate 1s infinite linear;
                }
            </style>


            <!-- Modal -->
            <div class="modal fade" id="editUserModal" tabindex="-1" role="dialog" aria-labelledby="editUserModalLabel"
                aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="editUserModalLabel">Video Management</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <!-- Form fields to edit user details -->
                            <form>
                                <div class="form-group w-100">
                                    <label for="editUsername">Video ID</label>
                                    <input type="text" class="form-control" id="editVideoID" ng-model="vd.Id"
                                        disabled>
                                </div>
                                <div class="row">

                                    <div class="col-6">

                                        <div class="form-group">
                                            <label for="editPassword">Title</label>
                                            <input type="text" class="form-control" id="editTitle"
                                                ng-model="vd.title">
                                        </div>

                                        <div class="form-group">
                                            <label for="editPassword">URL</label>
                                            <input type="text" class="form-control" id="editURL"
                                                ng-model="vd.url">
                                        </div>
                                        <div class="form-group">
                                            <span class="me-2">Active: </span>
                                            <div class="d-flex">
                                                <div class="form-check" style="margin-right: 10px;">
                                                    <input ng-model="vd.active" class="form-check-input" type="radio" name="active" id="activeTrue" value="true" ng-checked="vd.active === true">

                                                    <label class="form-check-label" for="genderMale">
                                                        TRUE
                                                    </label>
                                                </div>
                                                <div class="form-check">
                                                    <input ng-model="vd.active" class="form-check-input" type="radio" name="active" id="activeFalse" value="false" ng-checked="vd.active === false">

                                                    <label class="form-check-label" for="genderFemale">
                                                        FALSE
                                                    </label>
                                                </div>
                                            </div>
                                        </div>

                                    </div>

                                    <div class="col-6">


                                        <div class="card p-1">
                                            <label for="editPassword">Poster</label>
                                            <input type="file" class="my-2" id="editPoster"
                                                ng-model="vd.poster">
                                            <img width="100%" height="80px" src="views/src/img/{{vd.poster}}" alt="{{vd.poster}}">
                                        </div>
                                        
                                        <div class="form-group">
                                            <label for="editPassword">Description</label>
                                            <textarea  class="form-control" id="editDes"
                                                ng-model="vd.des"> </textarea>
                                        </div>
    
                                        <!-- Add other input fields here -->
                                    </div>
                                </div>



                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary" ng-click="saveChanges()">Save changes</button>
                            <button type="button" class="btn btn-danger" ng-click="deleteUser()">Delete</button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Page Heading -->
            <h1 class="h3 mb-2 text-gray-800">Video Management</h1>
            <p class="mb-4">Quản lý video</p>

            <!-- DataTales Example -->
            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">Video Table <span id="rotate-button" ng-click="getAll()"
                            class=" btn btn-info btn-circle btn-sm">
                            <i class="fas fa-sync"></i>
                        </span></h6>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                            <thead>
                                <tr class="text-center">
                                    <th>#</th>
                                    <th>ID</th>
                                    <th>Title</th>
                                    <th>Description</th>
                                    <th>Views</th>
                                    <th>Poster</th>
                                    <th>URL</th>
                                    <th>Active</th>
                                    <th>Edit</th>
                                </tr>
                            </thead>
                            <tfoot>
                                <tr class="text-center">
                                    <th>#</th>
                                    <th>ID</th>
                                    <th>Title</th>
                                    <th>Description</th>
                                    <th>Views</th>
                                    <th>Poster</th>
                                    <th>URL</th>
                                    <th>Active</th>
                                    <th>Edit</th>
                                </tr>
                            </tfoot>
                            <tbody>
                                <tr ng-repeat="v in videos">
                                    <td class="text-center">{{$index+1}}</td>
                                    <td class="text-center">{{v.Id}}</td>
                                    <td class="text-center">{{v.title}}</td>
                                    <td class="text-center">{{v.des | limitTo:70}}</td>
                                    <td class="text-center">{{v.views}}</td>
                                    <td class="text-center"> <img width="70px" height="50px" src="views/src/img/{{v.poster}}" alt="{{v.poster}}"></td>
                                    <td class="text-center">{{v.url}}</td>
                                    <td class="text-center">{{v.active ? 'TRUE' : 'FALSE'}}</td>
                                    <td class="text-center">
                                        <!-- Nút chỉnh sửa -->
                                        <button ng-click="openEditModal(v)" class="btn btn-warning btn-circle btn-sm">
                                            <i class="fas fa-edit"></i>
                                        </button>

                                        <!-- Nút xóa -->
                                        <button class="btn btn-danger btn-circle btn-sm">
                                            <i class="fas fa-trash"></i>
                                        </button>
                                    </td>
                                </tr>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>





            <!-- Page level plugins -->
            <script src="Admin/vendor/datatables/jquery.dataTables.min.js"></script>
            <script src="Admin/vendor/datatables/dataTables.bootstrap4.min.js"></script>

            <!-- Page level custom scripts -->
            <script src="Admin/js/demo/datatables-demo.js"></script>