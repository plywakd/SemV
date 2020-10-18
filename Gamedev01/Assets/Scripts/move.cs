using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class move : MonoBehaviour
{
    private Rigidbody2D rb;
    // Start is called before the first frame update
    void Start()
    {
        rb = gameObject.GetComponent<Rigidbody2D>();
    }

    // Update is called once per frame

    private void Update()
    {
        
    }

    private void FixedUpdate()
    {
        float moveHorizontal = Input.GetAxis("Horizontal");
        float moveVertical = Input.GetAxis("Vertical");
        Vector2 vec = new Vector2(moveHorizontal, moveVertical);
        print("INPUT VECTOR:" + vec);
        // rb.AddForce(vec * 10.0f);
        rb.velocity = vec;
        //rb.MovePosition(vec);
    }
}
