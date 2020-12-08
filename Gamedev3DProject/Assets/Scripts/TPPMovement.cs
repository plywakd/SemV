using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TPPMovement : MonoBehaviour
{
    public CharacterController cc;
    public Transform tppCamera;
    float speed = 6f;
    float smoothTime = 0.1f;
    float smoothVelocity;
    // Start is called before the first frame update
    void Start()
    {
        cc = GetComponent<CharacterController>();
    }

    // Update is called once per frame
    void Update()
    {
        float xAxis = Input.GetAxisRaw("Horizontal");
        float zAxis = Input.GetAxisRaw("Vertical");
        Vector3 direction = new Vector3(xAxis, 0f, zAxis).normalized;

        if (direction.magnitude >= 0.1f){
            float targetAngle = Mathf.Atan2(direction.x, direction.z) * Mathf.Rad2Deg + tppCamera.eulerAngles.y;
            float angle = Mathf.SmoothDampAngle(transform.eulerAngles.y, targetAngle, ref smoothVelocity,smoothTime);
            transform.rotation = Quaternion.Euler(0f, angle, 0f);

            Vector3 moveDirection = Quaternion.Euler(0f, targetAngle, 0f) * Vector3.forward;
            cc.Move(moveDirection.normalized * speed * Time.deltaTime);
        }


    }
    void OnControllerColliderHit(ControllerColliderHit hit) {
        Rigidbody body = hit.collider.attachedRigidbody;
        if (body != null && !body.isKinematic) body.velocity += hit.controller.velocity/10f;
    }
}
