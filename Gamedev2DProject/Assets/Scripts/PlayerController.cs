using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class PlayerController : MonoBehaviour
{
    private Rigidbody2D rb;
    public Animator anim;
    public SpriteRenderer sr;
    public float speed;
    public float jumpForce;
    private float moveInput;

    private bool isOnGround;
    public Transform feetPosition;
    public float checkRadius;
    public LayerMask thisGround;
    // Start is called before the first frame update
    void Start()
    {
        rb = gameObject.GetComponent<Rigidbody2D>();
        sr = gameObject.GetComponent<SpriteRenderer>();
    }

    // Update is called once per frame
    void Update()
    {
        isOnGround = Physics2D.OverlapCircle(feetPosition.position, checkRadius, thisGround);

        if (isOnGround == true && Input.GetKeyDown(KeyCode.Space))
        {
            rb.velocity = Vector2.up * jumpForce;
            anim.SetBool("Jump", true);
        }

        if(isOnGround == true && Mathf.Abs(rb.velocity.y) < 0.1f)
        {
            anim.SetBool("Jump", false);
        }

        
        if (Input.GetKeyDown(KeyCode.Escape))
        {
            SceneManager.LoadScene("Menu");
        }
    }

    private void FixedUpdate()
    {
        moveInput = Input.GetAxisRaw("Horizontal");
        rb.velocity = new Vector2(moveInput * speed, rb.velocity.y);
        if(moveInput < 0)
        {
            sr.flipX = true;
        }
        else
        {
            sr.flipX = false;
        }
        anim.SetFloat("Speed", Mathf.Abs(moveInput));
    }

}