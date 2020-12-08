using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CharacterGravity : MonoBehaviour
{
    CharacterController cc;
    private float gravity = -9.81f;
    private float groundDistance = 0.1f;
    private float jumpHeight = 1f;

    private bool isOnGround;
    public Transform groundCheck;
    public LayerMask thisGround;

    private Vector3 velocity;

    // Start is called before the first frame update
    void Start()
    {
        cc = GetComponent<CharacterController>();
    }

    // Update is called once per frame
    void Update()
    {
        isOnGround = Physics.CheckSphere(groundCheck.position, groundDistance, thisGround);
        if (isOnGround && velocity.y < 0) velocity.y = -0.1f;

        if (Input.GetKeyDown(KeyCode.Space) && isOnGround) velocity.y = Mathf.Sqrt(jumpHeight * gravity * (-2));

        velocity.y += gravity * Time.deltaTime;
        cc.Move(velocity * Time.deltaTime);

    }
}
