package com.concord.coder.web;

import com.concord.coder.dto.ErrorResponseDTO;
import com.concord.coder.dto.UserNameRequestDTO;
import com.concord.coder.dto.UserNameResponseDTO;
import com.concord.coder.exceptions.UserNameCantBeObtainedException;
import com.concord.coder.services.UserNameService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users/names")
public class UserNameController {
  private static final Logger logger = LoggerFactory.getLogger(UserNameController.class.getName());

  private final UserNameService userService;

  @Autowired
  public UserNameController(UserNameService userService) {
    this.userService = userService;
  }

  @GetMapping("")
  public String getAll() {
    return "Hello to all!";
  }


  @PostMapping(params = "encrypted", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public UserNameResponseDTO getEncryptedFullUserName(@RequestBody UserNameRequestDTO userDTO) {
    int userId = userDTO.getId();
    logger.trace("Request for encrypted user name has been received: {}", userDTO);
    UserNameResponseDTO toSend = new UserNameResponseDTO(userService.getEncryptedFullName(userId), null);
    logger.trace("Response with encrypted user name for user id {} has been sent: {}", userId, toSend);
    return toSend;
  }

  @PostMapping(params = "decrypted", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public UserNameResponseDTO getDecryptedFullUserName(@RequestBody UserNameRequestDTO userDTO) {
    String encryptedFullUserName = userDTO.getEncryptedFullName();
    logger.trace("Request for decrypted user name by encrypted name {} has been received: {}", encryptedFullUserName, userDTO);
    UserNameResponseDTO toSend = new UserNameResponseDTO(null, userService.getFullName(encryptedFullUserName));
    logger.trace("Response for decrypted user name by encrypted name {} will be sent: {}", encryptedFullUserName, toSend);
    return toSend;
  }

  @ExceptionHandler(UserNameCantBeObtainedException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponseDTO handleException(RuntimeException ex) {
    logger.error(ex.getMessage() + ": " + ex.getCause());
    return new ErrorResponseDTO(400, "User full name could not be obtained", ex.getMessage(), "/users/names");
  }
}
