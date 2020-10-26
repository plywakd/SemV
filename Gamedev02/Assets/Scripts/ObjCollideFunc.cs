using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ObjCollideFunc : MonoBehaviour
{
    private Rigidbody2D rb;
    private Rigidbody2D player;
    private SpriteRenderer sr;
    private Vector3 startPos;
    private Vector3 startSize;
    private Color startColor;
    // Start is called before the first frame update
    void Start()
    {
        rb = gameObject.GetComponent<Rigidbody2D>();
        player = GameObject.Find("Player").GetComponent<Rigidbody2D>();
        sr = player.GetComponent<SpriteRenderer>();
        startPos = player.transform.position;
        startSize = player.transform.localScale;
        startColor = sr.color;
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    void OnCollisionEnter2D(Collision2D col)
    {
        if(col.collider.name == "Player")
        {
            sr.color = Color.blue;
        }
    }

    void OnCollisionStay2D(Collision2D collision)
    {
        if (collision.collider.name == "Player")
        {
            player.transform.localScale += new Vector3(0.01f, 0.01f, 0.0f);
        }
    }

    void OnCollisionExit2D(Collision2D collision)
    {
        if (collision.collider.name == "Player")
        {
            player.transform.position = startPos;
            player.transform.localScale = startSize;
            sr.color = startColor;
        }
    }
}
