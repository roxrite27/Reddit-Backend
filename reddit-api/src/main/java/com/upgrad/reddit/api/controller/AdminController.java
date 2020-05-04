package com.upgrad.reddit.api.controller;

import com.upgrad.reddit.api.model.UserDeleteResponse;
import com.upgrad.reddit.service.business.AdminBusinessService;
import com.upgrad.reddit.service.entity.UserEntity;
import com.upgrad.reddit.service.exception.AuthorizationFailedException;
import com.upgrad.reddit.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

   
 @Autowired
    private AdminBusinessService adminBusinessService;

    /**
     * A controller method to delete a user in the database.
     *
     * @param userId        - The uuid of the user to be deleted from the database.
     * @param authorization - A field in the request header which contains the JWT token.
     * @return - ResponseEntity<UserDeleteResponse> type object along with Http status OK.
     * @throws AuthorizationFailedException
     * @throws UserNotFoundException
     */

    @PostMapping("/deleteUser")
    public ResponseEntity<UserDeleteResponse> deleteUser(@RequestBody String userId, @RequestHeader String authorization) throws AuthorizationFailedException,UserNotFoundException {
            
      UserEntity userEntity = adminBusinessService.deleteUser(authorization,userId);

            return new ResponseEntity<UserDeleteResponse>(new UserDeleteResponse().id(userId),HttpStatus.OK);

    }
}
