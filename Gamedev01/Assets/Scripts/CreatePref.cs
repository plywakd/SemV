using JetBrains.Annotations;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CreatePref : MonoBehaviour
{
    public GameObject to_create;
    // Start is called before the first frame update
    void Start()
    {
        Instantiate(to_create, new Vector2(0.0f, 0.0f), Quaternion.identity);
    }

    // Update is called once per frame
    void Update()
    {
        if (Input.GetKeyDown(KeyCode.UpArrow))
        {
            Instantiate(to_create, new Vector2(0.0f,0.0f), Quaternion.identity);
        }
        if (Input.GetKeyDown(KeyCode.Space))
        {
            Destroy(GameObject.FindGameObjectWithTag("Player"));
        }
    }
}
