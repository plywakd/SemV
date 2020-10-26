using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CandyPoint : MonoBehaviour
{
    private GameObject gm;
    // Start is called before the first frame update
    void Start()
    {
        gm = GameObject.FindGameObjectWithTag("PointsMenager");   
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    private void OnTriggerEnter2D(Collider2D collision)
    {
        if(collision.name == "Player")
        {
            Debug.Log("Candy!");
        }
    }
}
